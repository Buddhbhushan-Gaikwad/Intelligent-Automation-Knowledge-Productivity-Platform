-- File: src/main/resources/db/migration/V1__init_schema.sql

-- ===========================
-- USERS TABLE
-- ===========================
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===========================
-- USER ROLES
-- ===========================
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- ===========================
-- JOURNAL ENTRIES
-- ===========================
CREATE TABLE journal_entries (
    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(200) NOT NULL,

    content TEXT NOT NULL,

    user_id BIGINT NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_journal_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

-- ===========================
-- INDEXES
-- ===========================
CREATE INDEX idx_users_username
ON users(username);

CREATE INDEX idx_journal_user_created
ON journal_entries(user_id, created_at DESC);