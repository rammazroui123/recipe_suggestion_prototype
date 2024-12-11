import { useState, useEffect } from "react";

const cache = new Map(); // Caching for query results

const useFetchRecipes = (query) => {
  const [recipes, setRecipes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const controller = new AbortController();
    const signal = controller.signal;

    const fetchRecipes = async () => {
      if (cache.has(query)) {
        setRecipes(cache.get(query));
        return;
      }

      setLoading(true);
      setError(null);

      try {
        const response = await fetch(
          `http://localhost:8080/api/recipes?query=${query}`,
          { signal }
        );
        if (!response.ok) throw new Error("Failed to fetch recipes");

        const data = await response.json();
        cache.set(query, data); // Cache the results
        setRecipes(data);
      } catch (err) {
        if (!signal.aborted) setError(err.message);
      } finally {
        if (!signal.aborted) setLoading(false);
      }
    };

    if (query) fetchRecipes();

    return () => {
      controller.abort(); // Cancel ongoing requests on unmount
    };
  }, [query]);

  return { recipes, loading, error };
};

export default useFetchRecipes;
