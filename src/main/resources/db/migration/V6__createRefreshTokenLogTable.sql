CREATE TABLE refresh_token_log (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    refresh_token_id VARCHAR(255) NOT NULL,
    issued_at TIMESTAMP NOT NULL,
    expires_at TIMESTAMP,
    revoked BOOLEAN DEFAULT FALSE,
    user_agent TEXT,
    ip_address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_refresh FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
);