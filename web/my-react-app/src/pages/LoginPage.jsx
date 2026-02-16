import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { setToken } from "../security/auth";
import "../css/auth.css";

const LoginPage = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState({ email: "", password: "" });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    if (!form.email || !form.password) {
      setError("Please provide your credentials.");
      return;
    }

    setIsSubmitting(true);
    try {
      const response = await fetch(`http://localhost:8080/api/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          email: form.email.trim(),
          password: form.password,
        }),
      });

      if (response.ok) {
        const data = await response.json();
        if (!data?.token) {
          throw new Error("Security token missing.");
        }
        setToken(data.token);
        navigate("/dashboard", { replace: true });
      } else {
        // Try to parse error message, fallback to generic
        const message = await response.text();
        setError(message || "Invalid email or password.");
      }
    } catch (err) {
      setError("System unreachable. Please try again later.");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="auth-page">
      <form className="auth-card" onSubmit={handleSubmit}>
        <div className="auth-header">
          <h2 className="auth-title">Welcome Back</h2>
          <p className="auth-subtitle">Sign in to the secure portal</p>
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
            placeholder="name@company.com"
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
            placeholder="••••••••"
            required
          />
        </div>

        <button className="auth-button" type="submit" disabled={isSubmitting}>
          {isSubmitting ? "Authenticating..." : "Sign In"}
        </button>

        <p className="auth-footer">
          New to the system? <Link to="/register" className="auth-link">Create an account</Link>
        </p>
      </form>
    </div>
  );
};

export default LoginPage;