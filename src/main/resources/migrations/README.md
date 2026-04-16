# Database Migrations

This folder contains SQL migration scripts for the Token Manager database.

## Migration Files

- `V1__create_base_tables.sql` - Creates all base tables and relationships
- `V2__insert_base_data.sql` - Inserts initial data (attributes, expertises, roles, origins)

## Database Schema

### Tables

1. **attribute** - Character attributes (AGI, INT, PRE, STR, VIG)
2. **expertise** - Skills/expertises with their base attributes
3. **base_status** - HP/EP/SAN progression for roles
4. **role** - Character roles (Combatant, Specialist, Occultist)
5. **origin** - Character origins with special powers
6. **token** - Player characters/tokens
7. **role_expertise** - Junction table for role expertises
8. **origin_expertise** - Junction table for origin expertises
9. **token_expertise** - Junction table for token expertises

## Running Migrations

You can use tools like Flyway or Liquibase to run these migrations, or execute them manually:

```bash
# PostgreSQL
psql -U postgres -d token_manager -f V1__create_base_tables.sql
psql -U postgres -d token_manager -f V2__insert_base_data.sql
```

## Notes

- The schema uses SERIAL for auto-incrementing primary keys
- Foreign key constraints ensure referential integrity
- Junction tables handle many-to-many relationships
- NEX values are constrained between 0 and 99
