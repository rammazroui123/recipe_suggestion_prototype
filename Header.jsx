// src/components/Header.jsx
import React from "react";
import styled from "styled-components";

// Styled Components
const HeaderWrapper = styled.header`
  background-color: #0a74da;
  color: white;
  padding: 1rem;
  text-align: center;
`;

const Header = () => (
  <HeaderWrapper>
    <h1>Smart House Recipe Suggestion Tool</h1>
  </HeaderWrapper>
);

export default Header;
