import React from "react";
import "../CSS/Popup.css";
import Button from "./Button";

function Popup({ message,confirmText, onClose,onConfirm  }) {
  return (
    <div className="popup">
      <div className="popup-content">
        <p>{message}</p>
        {confirmText && (
          <Button className="confirm-button" text={confirmText} onClick={onConfirm} />
        )}
        <Button className="close-button" text="Close" onClick={onClose} />
      </div>
    </div>
  );
}

export default Popup;