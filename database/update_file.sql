--Altering the Users table
-- Step 1: Add the password column to the Users table
ALTER TABLE Users
ADD COLUMN password VARCHAR(100);

-- Step 2: Update the password to 'default_password' where it is NULL
UPDATE Users
SET password = 'default_password'
WHERE password IS NULL;
