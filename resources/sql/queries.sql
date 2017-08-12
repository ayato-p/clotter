-- :name create-user! :<!
-- :doc creates a new user record
INSERT INTO user
(id, email, pass)
VALUES (:id, :email, :pass)

-- :name update-email! :<!
-- :doc update an existing user record
UPDATE user
SET email = :email
WHERE id = :id

-- :name fetch-user :? :1
-- :doc retrieve a user given the id.
SELECT * FROM user
WHERE id = :id

-- :name list-users :? :*
-- :doc list all users
SELECT * FROM user

-- :name withdraw-user :<!
-- :doc withdraw user
UPDATE user
SET is_active = false
where id = :id

-- :name show-timeline :? :*
SELECT id, user_id, content, created_timestamp, from_id as followed_by
FROM tweet
INNER JOIN follow
    ON user_id = to_id
HAVING followed_by = :user_id
ORDER BY id DESC
LIMIT :limit
OFFSET :offset;
