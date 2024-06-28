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
        register: resolve(__dirname, "register.html"),
        customer_account: resolve(__dirname, "customer_dashboard.html"),
        admin_account: resolve(__dirname, "admin_dashboard.html"),
        afspraak_maken: resolve(__dirname, "afspraak_maken.html"),
        afspraak_beheren: resolve(__dirname, "afspraak_beheren.html"),
        review_achterlaten: resolve(__dirname, "review_achterlaten.html"),
        review_beheren: resolve(__dirname, "review_beheren.html"),
      },
    },
    outDir: "../src/main/webapp/",
  },
});
