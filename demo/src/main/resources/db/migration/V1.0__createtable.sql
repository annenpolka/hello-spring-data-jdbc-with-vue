CREATE TABLE todo (
    -- idに自動採番するオプションをつけている
    todo_id INTEGER GENERATED ALWAYS AS IDENTITY,
    todo_title TEXT NOT NULL,
    finished boolean NOT NULL,
    created_at timestamp,
    PRIMARY KEY (todo_id)
);