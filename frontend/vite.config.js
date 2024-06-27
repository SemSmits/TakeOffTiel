/* eslint-disable import/no-extraneous-dependencies */
// vite.config.js
import { register } from "module";
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
        customer_account: resolve(__dirname, "customer_dashboard.html"),
        admin_account: resolve(__dirname, "admin_dashboard.html"),
        register: resolve(__dirname, "register.html"),
      },
    },
    outDir: "../src/main/webapp/",
  },
});
