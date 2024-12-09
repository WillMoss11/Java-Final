-- Create the users table to store user information
CREATE TABLE users (
    id SERIAL PRIMARY KEY,              -- Auto-incremented user ID
    username VARCHAR(255) NOT NULL,      -- Username for login
    email VARCHAR(255) NOT NULL,         -- User's email address
    password VARCHAR(255) NOT NULL,      -- User's password (ensure this is hashed in real applications)
    role VARCHAR(50) NOT NULL            -- Role can be 'buyer', 'seller', or 'admin'
);

-- Create the products table to store product information
CREATE TABLE products (
    id SERIAL PRIMARY KEY,              -- Auto-incremented product ID
    name VARCHAR(255) NOT NULL,          -- Product name
    price DECIMAL(10, 2) NOT NULL,       -- Product price
    quantity INT NOT NULL,              -- Product quantity
    seller_id INT REFERENCES users(id), -- Seller is a foreign key to the users table
    CONSTRAINT fk_seller FOREIGN KEY(seller_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Insert some sample roles into the users table (optional, for testing purposes)
INSERT INTO users (username, email, password, role) VALUES
('adminuser', 'admin@ecommerce.com', 'adminpassword', 'admin'),
('buyeruser', 'buyer@ecommerce.com', 'buyerpassword', 'buyer'),
('selleruser', 'seller@ecommerce.com', 'sellerpassword', 'seller');

-- Example of creating an index for faster searches on common queries (optional)
CREATE INDEX idx_users_username ON users (username);
CREATE INDEX idx_products_name ON products (name);
