# Poised project manager
###### Application that creates and manages project information.

## Application functions
1. Create new projects.
2. Edit existing project information.
3. Finalize and invoice projects.
4. View all ongoing projects.
5. View all overdue projects.
6. Find a specific project.

## Database notes
Data is managed with mySQL poisePMS database.
5 Parent tables are all referenced through 1 child tables.
Refereces are done through auto incrementing unique id's which are
the primary keys for all parent tables. These keys are added as
foreign keys to the child table.

## Database table information
1. Login table with user login credentials (parent table)
2. Projects table with all project object info (parent table)
3. Architects table with all architect object info (parent table)
4. Contractors table with all contractor object info (parent table)
5. Customers table with all customer object info (parent table)
6. Project overview table that links all tables together (child table)
