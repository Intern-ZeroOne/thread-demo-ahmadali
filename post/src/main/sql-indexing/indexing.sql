-- post table dagi user_id ustunu uchun GiST index yaratdik
CREATE INDEX user_id_index ON post USING GIST (user_id);
-- post table dagi likes ustunu uchun GiST index yaratdik
CREATE INDEX likes_index ON post USING GIST (likes);

-- GiST Index:
-- Generalized Search Tree (GiST) indexes are versatile index structures that support various data types beyond simple scalar values.
-- GiST indexes enable efficient searching and retrieval for complex data structures such as geometric objects, text documents, arrays, and more.
-- They are based on the concept of multidimensional trees, allowing for flexible search operations.
-- GiST indexes can handle different search predicates, including equality, range, and spatial operations like overlaps, containment, and distance-based searches.

