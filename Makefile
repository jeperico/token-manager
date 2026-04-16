.PHONY: help build up down restart logs clean test migrate db-shell pgadmin rebuild status

# Default target
help:
	@echo "Token Manager - Available Commands"
	@echo "===================================="
	@echo ""
	@echo "Docker Commands:"
	@echo "  make up          - Start all services (PostgreSQL + pgAdmin + App)"
	@echo "  make down        - Stop all services"
	@echo "  make restart     - Restart all services"
	@echo "  make rebuild     - Rebuild and restart all services"
	@echo "  make logs        - View logs from all services"
	@echo "  make logs-app    - View application logs"
	@echo "  make logs-db     - View database logs"
	@echo "  make logs-pgadmin - View pgAdmin logs"
	@echo "  make status      - Show status of all services"
	@echo ""
	@echo "Database Commands:"
	@echo "  make migrate     - Run database migrations"
	@echo "  make db-shell    - Open PostgreSQL shell"
	@echo "  make db-reset    - Reset database (WARNING: deletes all data)"
	@echo "  make db-view     - View all database data"
	@echo "  make db-clean-duplicates - Remove duplicate base_status entries"
	@echo ""
	@echo "Development Commands:"
	@echo "  make build       - Build the Java application"
	@echo "  make test        - Run tests"
	@echo "  make clean       - Clean build artifacts and Docker volumes"
	@echo "  make pgadmin     - Open pgAdmin in browser"
	@echo ""
	@echo "Utility Commands:"
	@echo "  make ps          - List running containers"
	@echo "  make exec-app    - Execute bash in app container"
	@echo "  make exec-db     - Execute bash in database container"

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

logs-app:
	docker-compose logs -f app

logs-db:
	docker-compose logs -f postgres

logs-pgadmin:
	docker-compose logs -f pgadmin

# Show status
status:
	docker-compose ps

# Build application
build:
	@echo "Building Java application..."
	mvn clean package

# Run tests
test:
	@echo "Running tests..."
	mvn test

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
	@echo "=== EXPERTISES ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "SELECT id, name, base_attribute_id FROM expertise ORDER BY id LIMIT 30;"
	@echo ""
	@echo "=== BASE STATUS ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "SELECT * FROM base_status LIMIT 30;"
	@echo ""
	@echo "=== ROLES ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "SELECT id, name, base_expertises, base_status_id FROM role LIMIT 30;"
	@echo ""
	@echo "=== ORIGINS ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "SELECT id, name, power_name FROM origin LIMIT 30;"
	@echo ""
	@echo "=== TOKENS ==="
	@docker exec -it token_manager_db psql -U postgres -d token_manager -c "SELECT * FROM token LIMIT 30;"

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

# Clean everything
clean:
	@echo "Cleaning build artifacts and Docker volumes..."
	mvn clean
	docker-compose down -v
	@echo "Clean complete!"

# Open pgAdmin in browser
pgadmin:
	@echo "Opening pgAdmin in browser..."
	@which xdg-open > /dev/null && xdg-open http://localhost:5050 || \
	which open > /dev/null && open http://localhost:5050 || \
	echo "Please open http://localhost:5050 in your browser"

# List containers
ps:
	docker-compose ps

# Execute bash in app container
exec-app:
	docker exec -it token_manager_app /bin/sh

# Execute bash in database container
exec-db:
	docker exec -it token_manager_db /bin/bash

# Install dependencies
install:
	@echo "Installing dependencies..."
	mvn dependency:resolve

# Run application locally (without Docker)
run-local:
	@echo "Running application locally..."
	mvn exec:java -Dexec.mainClass="com.softec.Main"

# Package application
package:
	@echo "Packaging application..."
	mvn package -DskipTests

# Development mode - watch for changes
dev:
	@echo "Starting development mode..."
	docker-compose up --build

# Quick start (build + up)
start: build up
	@echo "Application started successfully!"

# Full reset and restart
reset: clean up migrate
	@echo "Full reset complete!"
