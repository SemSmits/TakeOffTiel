/* eslint-disable import/no-extraneous-dependencies */
// vite.config.js
import { resolve } from "path";
import { defineConfig } from "vite";

export default defineConfig({
  build: {
    sourcemap: true,
    rollupOptions: {
      input: {
        main: resolve(__dirname, "index.html"),
        about: resolve(__dirname, "about.html"),
        contact: resolve(__dirname, "contact.html"),
        login: resolve(__dirname, "login.html"),
        account: resolve(__dirname, "account.html"),
      },
    },
    outDir: "../src/main/webapp/",
  },
});
