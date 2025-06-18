# 🧠 Voice Assistant for Developers

Um assistente virtual controlado por voz, desenvolvido em Kotlin e utilizando a engine de reconhecimento VOSK. Criado para aumentar a produtividade de desenvolvedores ao permitir a execução de comandos, automações e scripts personalizados por meio da fala.

## 🚀 Funcionalidades

- 🎙️ Reconhecimento de voz offline com [VOSK](https://alphacephei.com/vosk/)
- 🖥️ Execução de comandos de terminal por voz
- 🌐 Abertura de sites e navegadores com comandos como `"open chrome"` ou `"abrir site github.com"`
- 📦 Início/parada de containers com comandos como `"start containers"`
- 🧠 Comandos customizáveis com persistência
- 🖱️ Atalho de teclado global para ativar o assistente
- 📌 Ícone fixado na Dash (Linux)

## 📦 Tecnologias

- Kotlin
- VOSK Speech Recognition
- Java Sound API (para captura do microfone)
- zsh/bash para execução de comandos
- Linux Desktop Entry (`.desktop`) para integração com o sistema

## 📷 Screenshot (opcional)

> *(Adicione uma imagem ou gif da interface de voz ou da execução de comandos por voz)*

## ⚙️ Instalação

### 1. Pré-requisitos

- Java 21+
- Kotlin
- VOSK Model (baixe de: https://alphacephei.com/vosk/models)
- Linux com terminal (preferencialmente zsh)
- Permissões de microfone

### 2. Clonando o projeto

```bash
git clone https://github.com/seu-usuario/voice-assistant.git
cd voice-assistant
