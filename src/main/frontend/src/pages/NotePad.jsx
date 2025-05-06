import React, { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import "../styles/notepad.css";

const NotePad = () => {
  const { pageId } = useParams();
  const [content, setContent] = useState("");
  const clientRef = useRef(null);

  useEffect(() => {
    axios.get(`/api/notes/${pageId}`)
      .then((res) => setContent(res.data.content))
      .catch(() => setContent(""));

    const socket = new SockJS("/ws");
    const client = new Client({
      webSocketFactory: () => socket,
      onConnect: () => {
        client.subscribe(`/topic/notes/${pageId}`, (msg) => {
          console.log("Received message:", msg);
          if (msg.body) {
            setContent(msg.body);
            }
        });
      }
    });

    client.activate();
    clientRef.current = client;

    return () => {
      client.deactivate();
    };
  }, [pageId]);

  const handleChange = (e) => {
    const newText = e.target.value;
    setContent(newText);
    if (clientRef.current?.connected) {
      clientRef.current.publish({
        destination: `/app/notes/${pageId}`,
        body: newText
      });
    }
  };

  return (
    <div className="notepad-window">
      <div className="title-bar">Jaumpad - {pageId}</div>
      <textarea
        className="notepad-area"
        value={content}
        onChange={handleChange}
        spellCheck={false}
      />
    </div>
  );
};

export default NotePad;