package com.project1.usermanagementapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = "text/html")
    public String home() {
        return """
                <!doctype html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>User Management API</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 40px;
                            background: #f6f8fa;
                            color: #24292f;
                        }

                        .card {
                            max-width: 720px;
                            background: #ffffff;
                            border: 1px solid #d0d7de;
                            border-radius: 12px;
                            padding: 24px;
                        }

                        a {
                            display: block;
                            margin: 12px 0;
                            color: #0969da;
                            font-size: 18px;
                        }

                        code {
                            background: #f6f8fa;
                            padding: 2px 6px;
                            border-radius: 6px;
                        }
                    </style>
                </head>
                <body>
                    <main class="card">
                        <h1>User Management API</h1>
                        <p>Available endpoints:</p>

                        <a href="/api/users">GET /api/users</a>
                        <a href="/api/users/1">GET /api/users/1</a>

                        <p>
                            These links are relative, so they work in localhost and GitHub Codespaces.
                        </p>

                        <p>
                            Use Postman, curl, or the Angular UI to test POST, PUT, and DELETE.
                        </p>
                    </main>
                </body>
                </html>
                """;
    }
}