import React from 'react';

import './error-indicator.css';
import icon from './error-icon.png';

const ErrorIndicator = () => {
  return (
    <div className="error-indicator">
      <img src={icon} alt="Error icon" />
      <span className="boom">OOPS!</span>
      <span>
        Something went wrong.
      </span>
      <span>
        (but we already sent droids to fix it)
      </span>
    </div>
  );
};

export default ErrorIndicator;