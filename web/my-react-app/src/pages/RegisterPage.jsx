import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../css/auth.css";

const API_BASE = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

const RegisterPage = () => {
  const navigate = useNavigate();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState(null);
  const [form, setForm] = useState({
    email: "",
    password: "",
    confirmPassword: "",
    // address: "",
    // phoneNumber: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    // 1. Basic Validation
    if (!form.email || !form.password || !form.confirmPassword) {
      setError("All fields are required.");
      return;
    }

    if (form.password !== form.confirmPassword) {
      setError("Passwords do not match.");
      return;
    }

    setIsSubmitting(true);

    try {
      // 2. Prepare Payload (Remove confirmPassword before sending)
      const payload = {
        email: form.email,
        password: form.password,
        // address: form.address,
        // phoneNumber: form.phoneNumber
      };

      const response = await fetch(`${API_BASE}/api/auth/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        // Try to parse JSON error, fallback to text
        let errorMessage = "Registration failed.";
        try {
            const errData = await response.json();
            errorMessage = errData.message || errorMessage;
        } catch (e) {
            errorMessage = await response.text();
        }
        throw new Error(errorMessage);
      }

      // 3. Success
      alert("Account created successfully. Please sign in.");
      navigate("/login");

    } catch (err) {
      setError(err.message || "Unable to register right now.");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="auth-page">
      <form className="auth-card" onSubmit={handleSubmit}>
        <div className="auth-header">
          <h2 className="auth-title">Create Account</h2>
          <p className="auth-subtitle">Register for system access</p>
        </div>

        {error && <div className="auth-error">{error}</div>}

        <div className="form-group">
          <label className="auth-label" htmlFor="email">Email Address</label>
          <input
            id="email"
            name="email"
            type="email"
            value={form.email}
            onChange={handleChange}
            className="auth-input"
            required
          />
        </div>

        <div className="form-group">
          <label className="auth-label" htmlFor="password">Password</label>
          <input
            id="password"
            name="password"
            type="password"
            value={form.password}
            onChange={handleChange}
            className="auth-input"
            required
          />
        </div>

        <div className="form-group">
          <label className="auth-label" htmlFor="confirmPassword">Confirm Password</label>
          <input
            id="confirmPassword"
            name="confirmPassword"
            type="password"
            value={form.confirmPassword}
            onChange={handleChange}
            className="auth-input"
            required
          />
        </div>

        {/* <div className="form-group">
          <label className="auth-label">Phone Number</label>
          <input
             name="phoneNumber"
             value={form.phoneNumber}
             onChange={handleChange}
             className="auth-input"
          />
        </div>
        */}

        <button className="auth-button" type="submit" disabled={isSubmitting}>
          {isSubmitting ? "Creating Account..." : "Register"}
        </button>

        <p className="auth-footer">
          Already have an account? <Link to="/login" className="auth-link">Sign In</Link>
        </p>
      </form>
    </div>
  );
};

export default RegisterPage;