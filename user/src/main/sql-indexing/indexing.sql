--users tabledagi email ustuni uchun GiST index yaratdik
CREATE INDEX email_index ON users USING GIST (email);
--users tabledagi username ustuni uchun GiST index yaratdik
CREATE INDEX username_index ON users (username);
--users tabledagi uuid_for_email ustuni uchun GiST index yaratdik
CREATE INDEX uuid_index ON users (uuid_for_email);

-- GiST Index:
-- Generalized Search Tree (GiST) indexes are versatile index structures that support various data types beyond simple scalar values.
-- GiST indexes enable efficient searching and retrieval for complex data structures such as geometric objects, text documents, arrays, and more.
-- They are based on the concept of multidimensional trees, allowing for flexible search operations.
-- GiST indexes can handle different search predicates, including equality, range, and spatial operations like overlaps, containment, and distance-based searches.
