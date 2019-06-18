set PGPASSWORD=postgres123
cd util
pg_restore.exe -c --host localhost --port 5432 --username "postgres" --dbname "sisdental" --verbose --no-password "backup.backup"