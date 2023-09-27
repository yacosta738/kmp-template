# Kotlin Multiplatform Template
[![CI Build](https://github.com/yacosta738/kmp-template/actions/workflows/continuous-integration.yml/badge.svg)](https://github.com/yacosta738/kmp-template/actions/workflows/continuous-integration.yml)
![coverage](.github/badges/jacoco.svg)
[![semantic-release](https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg)](https://github.com/semantic-release/semantic-release)
[![Renovate enabled](https://img.shields.io/badge/renovate-enabled-brightgreen.svg)](https://renovatebot.com/)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=kmp-template)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=kmp-template)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=kmp-template)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=kmp-template)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=kmp-template)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=bugs)](https://sonarcloud.io/summary/new_code?id=kmp-template)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=kmp-template)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=kmp-template)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=kmp-template&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=kmp-template)

This is a template for a Kotlin Multiplatform project. It is configured to build a library for Android, iOS, and the JVM. It also includes a sample Android app and a sample iOS app.

## Documentation
The documentation for this project is available at [https://yacosta738.github.io/kmp-template](https://yacosta738.github.io/kmp-template/).   

## Setup
1. Clone this repository and change the remote to your own repository.
```bash
git clone https://github.com/yacosta738/kmp-template.git <your-project-name>
cd <your-project-name>
git remote set-url origin <your-repository-url>
```
2. Update the project name in `settings.gradle.kts`.
3. Update the package name of the all modules.
4. Update the project name in `README.md`.

### Starting running the project locally
1. Run the Android app
```bash
./gradlew :app:installDebug
```
2. Run the iOS app
```bash
./gradlew :ios-app:installDebug
```
3. Run desktop app
```bash
./gradlew :desktop-app:run
```
4. Run the Server
```bash
./gradlew :server:run
```

## CI/CD
This project uses GitHub Actions for CI/CD. The workflow is defined in `.github/workflows/continuous-integration.yml`. It is configured to run the tests, build the apps, and publish the artifacts to GitHub Packages.
