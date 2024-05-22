import globals from "globals";
import js from "@eslint/js";
import prettier from "eslint-config-prettier";
export default [
  js.configs.all,
  {
    languageOptions: {
      ecmaVersion: "latest",
      globals: globals.browser,
      sourceType: "module",
    },
    plugins: {
      prettier,
    },
    rules: {
      "no-console": "warn",
    },
  },
];
