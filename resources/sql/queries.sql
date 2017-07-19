-- :name create-user! :<!
-- :doc creates a new user record
INSERT INTO users
(id, email, pass)
VALUES (:id, :email, :pass)

-- :name update-email! :<!
-- :doc update an existing user record
UPDATE users
SET email = :email
WHERE id = :id

-- :name fetch-user :? :1
-- :doc retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- :name list-users :? :*
-- :doc list all users
SELECT * FROM users

-- :name withdraw-user :<!
-- :doc withdraw user
UPDATE users
SET is_active = false
where id = :id
