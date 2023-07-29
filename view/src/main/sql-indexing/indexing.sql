--users tabledagi email ustuni uchun GiST index yaratdik
CREATE INDEX user_id_index ON views USING GIST (user_id);
--users tabledagi username ustuni uchun GiST index yaratdik
CREATE INDEX post_id_index ON views USING GIST (post_id);

-- GiST Index:
-- Generalized Search Tree (GiST) indexes are versatile index structures that support various data types beyond simple scalar values.
-- GiST indexes enable efficient searching and retrieval for complex data structures such as geometric objects, text documents, arrays, and more.
-- They are based on the concept of multidimensional trees, allowing for flexible search operations.
-- GiST indexes can handle different search predicates, including equality, range, and spatial operations like overlaps, containment, and distance-based searches.
