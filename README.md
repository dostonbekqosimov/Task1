## API Endpoints

### User Endpoints:
- **POST** `/api/users/register`: Register a new user
- **GET** `/api/users`: Get all users
- **GET** `/api/users/{username}`: Get user by username

### Story Endpoints:
- **GET** `/api/stories`: Get all stories
- **GET** `/api/stories/{id}`: Get story by ID
- **POST** `/api/stories/story`: Add a new story
- **POST** `/api/stories/{storyId}/pages`: Add a new page to a story

### Reaction Endpoints:
- **GET** `/api/reactions/{pageId}/likes`: Get likes for a page
- **GET** `/api/reactions/{pageId}/dislikes`: Get dislikes for a page
- **PUT** `/api/reactions/{pageId}/like`: Like a page
- **PUT** `/api/reactions/{pageId}/dislike`: Dislike a page

### Page Endpoints:
- **GET** `/api/pages`: Get all pages
- **GET** `/api/pages/story/{storyId}`: Get all pages related to a story


## Database Setup

### Tables
- **users**: Contains information about users.
- **authorities**: Contains user roles/authorities.

### SQL Setup

```sql
-- Create authorities table
CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  CONSTRAINT authorities_pk PRIMARY KEY (username, authority),
  CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES users (username)
);

-- Insert data into authorities table
INSERT INTO authorities (username, authority)
VALUES 
    ('john', 'ROLE_USER'),
    ('mary', 'ROLE_USER'),
    ('susan', 'ROLE_USER'),
    ('susan', 'ROLE_ADMIN'),
    ('doston', 'ROLE_USER'),
    ('doston', 'ROLE_ADMIN');

-- Create users table
CREATE TABLE users (
  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  username VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN DEFAULT TRUE NOT NULL
);

-- Insert data into users table (password is test1)
INSERT INTO users (username, password, enabled)
VALUES 
    ('doston', '$2a$12$TxN2LNfDBpRRSfm4VyLVguEghcoU3mZhDdpCOYzGHQfdHONZYFJzm', true),
    ('susan', '$2a$12$TxN2LNfDBpRRSfm4VyLVguEghcoU3mZhDdpCOYzGHQfdHONZYFJzm', true),
    ('john', '$2a$12$TxN2LNfDBpRRSfm4VyLVguEghcoU3mZhDdpCOYzGHQfdHONZYFJzm', true),
    ('mary', '$2a$12$TxN2LNfDBpRRSfm4VyLVguEghcoU3mZhDdpCOYzGHQfdHONZYFJzm', true);
