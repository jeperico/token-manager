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

# Reset database
db-reset:
	@echo "WARNING: This will delete all data!"
	@read -p "Are you sure? [y/N] " -n 1 -r; \
	echo; \
	if [[ $$REPLY =~ ^[Yy]$$ ]]; then \
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
