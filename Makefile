.PHONY: help up down restart logs clean migrate db-shell pgadmin pgadmin-wsl rebuild status

# Default target
help:
	@echo "Token Manager - Available Commands"
	@echo "===================================="
	@echo ""
	@echo "Docker Commands:"
	@echo "  make up                  - Start all services (PostgreSQL + pgAdmin)"
	@echo "  make down                - Stop all services"
	@echo "  make restart             - Restart all services"
	@echo "  make rebuild             - Rebuild and restart all services"
	@echo "  make status              - Show status of all services"
	@echo ""
	@echo "Docker logs:"
	@echo "  make logs                - View logs from all services"
	@echo "  make logs-db             - View database logs"
	@echo "  make logs-pgadmin        - View pgAdmin logs"
	@echo ""
	@echo "Database Commands:"
	@echo "  make db-view             - View all database data"
	@echo "  make migrate             - Run database migrations"
	@echo "  make db-shell            - Open PostgreSQL shell"
	@echo "  make db-reset            - Reset database (WARNING: deletes all data)"
	@echo "  make db-clean-duplicates - Remove duplicate base_status entries"
	@echo ""
	@echo "Development Commands:"
	@echo ""
	@echo "Utility Commands:"
	@echo "  make pgadmin             - Open pgAdmin in browser"
	@echo "  make exec-db             - Execute bash in database container"

# Start all services
up:
	@echo "Starting all services..."
	docker-compose up -d
	@echo "Services started!"
	@echo "pgAdmin: http://localhost:5050 (admin@admin.com / admin)"
	@echo "PostgreSQL: localhost:5432 (postgres / postgres)"

# Stop all services
down:
	@echo "Stopping all services..."
	docker-compose down

# Restart all services
restart:
	@echo "Restarting all services..."
	docker-compose restart

# Rebuild and restart
rebuild:
	@echo "Rebuilding and restarting services..."
	docker-compose up -d --build

# View logs
logs:
	docker-compose logs -f

logs-db:
	docker-compose logs -f postgres

logs-pgadmin:
	docker-compose logs -f pgadmin

# Show status
status:
	docker-compose ps

# Run migrations
migrate:
	@echo "Running database migrations..."
	docker exec -i token_manager_db psql -U postgres -d token_manager < src/main/resources/migrations/V1__create_base_tables.sql
	docker exec -i token_manager_db psql -U postgres -d token_manager < src/main/resources/migrations/V2__insert_base_data.sql
	@echo "Migrations completed!"

# Open database shell
db-shell:
	@echo "Opening PostgreSQL shell..."
	docker exec -it token_manager_db psql -U postgres -d token_manager

# View database data
db-view:
	@echo "=== ATTRIBUTES ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "SELECT * FROM attribute LIMIT 30;"
	@echo ""
	@echo "=== EXPERTISES (with Attribute) ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "\
		SELECT e.id, e.name, a.short_name as attribute, e.trained_only, e.charge_penalty, e.kit_needed \
		FROM expertise e \
		JOIN attribute a ON e.base_attribute_id = a.id \
		ORDER BY e.name LIMIT 30;"
	@echo ""
	@echo "=== BASE STATUS ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "SELECT * FROM base_status LIMIT 30;"
	@echo ""
	@echo "=== ROLES (with Status & Expertises) ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "\
		SELECT r.id, r.name, r.base_expertises, \
		       CONCAT('HP:', bs.hp_base, '+', bs.hp_level, ' EP:', bs.ep_base, '+', bs.ep_level, ' SAN:', bs.san_base, '+', bs.san_level) as status, \
		       STRING_AGG(e.name, ', ') as expertises \
		FROM role r \
		JOIN base_status bs ON r.base_status_id = bs.id \
		LEFT JOIN role_expertise re ON r.id = re.role_id \
		LEFT JOIN expertise e ON re.expertise_id = e.id \
		GROUP BY r.id, r.name, r.base_expertises, bs.hp_base, bs.hp_level, bs.ep_base, bs.ep_level, bs.san_base, bs.san_level \
		ORDER BY r.name LIMIT 30;"
	@echo ""
	@echo "=== ORIGINS (with Expertises) ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "\
		SELECT o.id, o.name, o.power_name, \
		       STRING_AGG(e.name, ', ') as expertises \
		FROM origin o \
		LEFT JOIN origin_expertise oe ON o.id = oe.origin_id \
		LEFT JOIN expertise e ON oe.expertise_id = e.id \
		GROUP BY o.id, o.name, o.power_name \
		ORDER BY o.name LIMIT 30;"
	@echo ""
	@echo "=== TOKENS (with Origin, Role & Expertises) ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "\
		SELECT t.id, t.name, t.nex, \
		       o.name as origin, r.name as role, \
		       STRING_AGG(e.name, ', ') as expertises \
		FROM token t \
		JOIN origin o ON t.origin_id = o.id \
		JOIN role r ON t.role_id = r.id \
		LEFT JOIN token_expertise te ON t.id = te.token_id \
		LEFT JOIN expertise e ON te.expertise_id = e.id \
		GROUP BY t.id, t.name, t.nex, o.name, r.name \
		ORDER BY t.name LIMIT 30;"

# Clean duplicate base_status entries
db-clean-duplicates:
	@echo "Cleaning duplicate base_status entries..."
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "\
		DELETE FROM base_status a USING base_status b \
		WHERE a.id > b.id \
		AND a.hp_base = b.hp_base \
		AND a.hp_level = b.hp_level \
		AND a.ep_base = b.ep_base \
		AND a.ep_level = b.ep_level \
		AND a.san_base = b.san_base \
		AND a.san_level = b.san_level;"
	@echo "Duplicates removed!"


# Reset database
db-reset:
	@echo "WARNING: This will delete all data!"
	@echo "Are you sure? [y/N]"
	@read answer; \
	if [ "$$answer" = "y" ] || [ "$$answer" = "Y" ]; then \
		docker-compose down -v; \
		docker-compose up -d; \
		echo "Database reset complete!"; \
	else \
		echo "Cancelled."; \
	fi

# Open pgAdmin in browser
pgadmin:
	@echo "Opening pgAdmin in browser..."
	@which xdg-open > /dev/null && xdg-open http://localhost:5050 || \
	which open > /dev/null && open http://localhost:5050 || \
	echo "Please open http://localhost:5050 in your browser"

# Open pgAdmin in browser on WSL
pgadmin-wsl:
	@echo "Opening pgAdmin in browser..."
	@cmd.exe /c start http://localhost:5050 2>/dev/null || \
	powershell.exe -Command "Start-Process http://localhost:5050" 2>/dev/null || \
	echo "Please open http://localhost:5050 in your browser"

# Execute bash in database container
exec-db:
	docker exec -it token_manager_db /bin/bash
