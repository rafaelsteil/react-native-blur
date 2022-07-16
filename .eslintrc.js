module.exports = {
  root: true,
  extends: ['plugin:react/recommended'],
  parser: '@typescript-eslint/parser',
  ignorePatterns: ['node_modules', 'android', 'ios', 'gradle'],
  parserOptions: {
    ecmaFeatures: {
      jsx: true,
    },
    ecmaVersion: 12,
    sourceType: 'module',
  },
  plugins: ['react', '@typescript-eslint'],
  rules: {},
};
