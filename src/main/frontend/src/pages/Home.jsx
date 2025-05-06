import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/home.css";

function Home() {
  const [pageId, setPageId] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (pageId.trim()) {
      navigate(`/pad/${pageId}`);
    }
  };

  return (
    <div className="home-container">
      <h1>Jaumpad</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter Page ID"
          value={pageId}
          onChange={(e) => setPageId(e.target.value)}
        />
        <button type="submit">Open</button>
      </form>
    </div>
  );
}

export default Home;