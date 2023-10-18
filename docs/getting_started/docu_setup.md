# Mkdocs Documentation

> "Material for MkDocs is a powerful documentation framework on top of MkDocs, a static site generator for project documentation.1 If you're familiar with Python, you can install Material for MkDocs with pip, the Python package manager. If not, we recommend using docker." (Martin Donath, 2023)

## Startup with docker-compose

When the docker-compose startup is used, the documentation is directly available
at http://localhost:8000.

## Local startup with Docker

Source: https://squidfunk.github.io/mkdocs-material/getting-started/

### Pull Image

```docker pull squidfunk/mkdocs-material```

### Start Mkdocs material

=== "Unix, Powershell"
    ```
    docker run --rm -it -p 8000:8000 -v ${PWD}:/docs squidfunk/mkdocs-material
    ```
=== "Windows"
    ```
    docker run --rm -it -p 8000:8000 -v "%cd%":/docs squidfunk/mkdocs-material
    ```

Point your browser to localhost:8000