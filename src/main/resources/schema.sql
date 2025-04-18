CREATE TABLE IF NOT EXISTS USERS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(50) NOT NULL UNIQUE,
    EMAIL VARCHAR(100) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS STATISTICS (
    user_id BIGINT PRIMARY KEY,
    total_games INT DEFAULT 0,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    average_moves_per_game INT DEFAULT 0,
    longest_win_streak INT DEFAULT 0,
    current_win_streak INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);