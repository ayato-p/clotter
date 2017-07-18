-- :name create-user! :! :n
-- :doc creates a new user record
INSERT INTO users
(id, email, pass)
VALUES (:id, :email, :pass)

-- :name update-user! :! :n
-- :doc update an existing user record
UPDATE users
SET email = :email
WHERE id = :id

-- :name get-user :? :1
-- :doc retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- :name delete-user! :! :n
-- :doc delete a user given the id
DELETE FROM users
WHERE id = :id

-- :name list-users :? :n
-- :doc list all users
SELECT * FROM users
