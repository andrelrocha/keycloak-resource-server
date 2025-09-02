CREATE TABLE audit_log_login (
    id VARCHAR(36) PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    login_time TIMESTAMP NOT NULL,
    logout_time TIMESTAMP,
    ip_address VARCHAR(45),
    login_status INT NOT NULL,  -- 0 = FAILURE, 1 = SUCCESS
    user_agent TEXT,
    host_name VARCHAR(255),
    server_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);