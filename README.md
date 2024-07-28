# Zerops x Spring

[Spring](https://spring.io/) is world's most popular Java framework
designed for building enterprise-level Java applications.
This recipe aims to showcase basic Spring concepts and how to integrate them with [Zerops](https://zerops.io),
all through a simple file upload demo application.

![spring](https://github.com/zeropsio/recipe-shared-assets/blob/main/covers/svg/cover-spring.svg)

<br/>

## Deploy on Zerops

You can either click the deploy button to deploy directly on Zerops, or manually copy
the [import yaml](https://github.com/zeropsio/recipe-spring/blob/main/zerops-project-import.yml)
to the import dialog in the Zerops app.

[![Deploy on Zerops](https://github.com/zeropsio/recipe-shared-assets/blob/main/deploy-button/green/deploy-button.svg)](https://app.zerops.io/recipe/spring)

<br/>

## Recipe features

- **Load balanced** Spring web app running on **Zerops Java** service
- Served by production-ready embedded application server **[Tomcat](https://spring.io/)**
- Zerops **PostgreSQL 16** service as database
- Zerops **Object Storage** (S3 compatible) service as file system
- Automatic on-startup Spring **database migrations**
- Utilization of Zerops built-in **environment variables** system
- Logs accessible through Zerops GUI
- **[Mailpit](https://github.com/axllent/mailpit)** as **SMTP mock server**
- **[Adminer](https://www.adminer.org)** for **quick database management** tool
- Unlocked development experience:
    - Access to database and mail mock through Zerops project VPN (`zcli vpn up`)
    - Prepared `.env.dist` file (`cp .env.dist .env` and change ***** secrets found in Zerops GUI)

<br/>

## Production vs. development

Base of the recipe is ready for production, the difference comes down to:

- Use highly available version of the PostgreSQL database (change `mode` from `NON_HA` to `HA` in recipe YAML, `db`
  service section)
- Use at least two containers for Spring service to achieve high reliability and resilience (add `minContainers: 2` in
  recipe YAML, `api` service section)
- Use production-ready third-party SMTP server instead of Mailpit (change `MAIL_` secret variables in recipe YAML `api`
  service)
- Disable public access to Adminer or remove it altogether (remove service `adminer` from recipe YAML)

<br/>

## Changes made over the default installation

If you want to modify your existing Spring app to efficiently run on Zerops, these are the general steps we took:

- Add [zerops.yml](https://github.com/zeropsio/recipe-spring/blob/main/zerops.yml) to your repository, our example
  includes migrations and build process

<br/>
<br/>

Need help setting your project up? Join [Zerops Discord community](https://discord.com/invite/WDvCZ54).
