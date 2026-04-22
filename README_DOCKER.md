# Token Manager - Docker Setup

This project is dockerized with PostgreSQL and pgAdmin.

## Prerequisites

- Docker
- Docker Compose

## Quick Start

### 1. Start all services

```bash
make up
```

This will start:
- PostgreSQL on port 5433
- pgAdmin on port 5050

### 2. Access pgAdmin

``` bash
make pgadmin
```
or
``` bash
make pgadmin-wsl
```

**Base login credentials:**
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

```bash
# See all commands available
make help
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

- **5433** - PostgreSQL
- **5050** - pgAdmin

## Volumes

- `postgres_data` - PostgreSQL data persistence
- `pgadmin_data` - pgAdmin configuration persistence

## Network

All services run on the `token_network` bridge network, allowing them to communicate using service names.
