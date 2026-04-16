# Token Manager - Docker Setup

This project is fully dockerized with PostgreSQL, pgAdmin, and the Java application.

## Prerequisites

- Docker
- Docker Compose

## Quick Start

### 1. Start all services

```bash
docker-compose up -d
```

This will start:
- PostgreSQL on port 5432
- pgAdmin on port 5050
- Java application

### 2. Access pgAdmin

Open your browser and go to:
```
http://localhost:5050
```

**Login credentials:**
- Email: `admin@admin.com`
- Password: `password`

### 3. Connect to PostgreSQL in pgAdmin

Once logged into pgAdmin:

1. Right-click "Servers" → "Register" → "Server"
2. **General tab:**
   - Name: `Token Manager DB`
3. **Connection tab:**
   - Host: `postgres`
   - Port: `5432`
   - Database: `token_manager`
   - Username: `postgres`
   - Password: `postgres`
4. Click "Save"

## Database Migrations

The database is automatically initialized with the migration scripts on first run:
- `V1__create_base_tables.sql` - Creates all tables
- `V2__insert_base_data.sql` - Inserts base data

## Useful Commands

### View logs
```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f postgres
docker-compose logs -f app
docker-compose logs -f pgadmin
```

### Stop services
```bash
docker-compose down
```

### Stop and remove volumes (clean slate)
```bash
docker-compose down -v
```

### Rebuild application
```bash
docker-compose up -d --build app
```

### Access PostgreSQL directly
```bash
docker exec -it token_manager_db psql -U postgres -d token_manager
```

### Run migrations manually
```bash
docker exec -i token_manager_db psql -U postgres -d token_manager < src/main/resources/migrations/V1__create_base_tables.sql
docker exec -i token_manager_db psql -U postgres -d token_manager < src/main/resources/migrations/V2__insert_base_data.sql
```

## Environment Variables

You can customize the setup by creating a `.env` file:

```env
# PostgreSQL
POSTGRES_DB=token_manager
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# pgAdmin
PGADMIN_EMAIL=admin@admin.com
PGADMIN_PASSWORD=admin
```

## Ports

- **5432** - PostgreSQL
- **5050** - pgAdmin

## Volumes

- `postgres_data` - PostgreSQL data persistence
- `pgadmin_data` - pgAdmin configuration persistence

## Network

All services run on the `token_network` bridge network, allowing them to communicate using service names.

## Troubleshooting

### Database not initialized
```bash
docker-compose down -v
docker-compose up -d
```

### Can't connect to database from pgAdmin
- Make sure to use `postgres` as the host (not `localhost`)
- Check that all services are running: `docker-compose ps`

### Application fails to start
```bash
# Check logs
docker-compose logs app

# Rebuild
docker-compose up -d --build app
```

## Development Workflow

1. Make changes to your code
2. Rebuild and restart the app:
   ```bash
   docker-compose up -d --build app
   ```
3. View logs:
   ```bash
   docker-compose logs -f app
   ```

## Production Considerations

For production, update:
- Change default passwords
- Use environment variables for sensitive data
- Add volume backups
- Configure proper logging
- Add health checks
- Use secrets management
