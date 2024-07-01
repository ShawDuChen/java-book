import { User } from "app";

const PREFIX = "SHOPPING_";
const USER = `${PREFIX}_USER`;
const TOKEN = `${PREFIX}_TOKEN`;

export const cacheUser = (user: Partial<User>) => {
  localStorage.setItem(USER, JSON.stringify(user));
}

export const readUser: () => Partial<User> = () => {
  const user = localStorage.getItem(USER);
  if (user) {
    return JSON.parse(user);
  }
  return {};
}

export const clearUser = () => {
  localStorage.removeItem(USER);
}

export const getToken = () => {
  return localStorage.getItem(TOKEN);
}

export const setToken = (token:string) => {
  return localStorage.setItem(TOKEN, token);
}

export const clearToken = () => {
  return localStorage.removeItem(TOKEN);
}
