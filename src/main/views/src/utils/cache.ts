import { User } from "app";

export const cacheUser = (user: Partial<User>) => {
  localStorage.setItem('user', JSON.stringify(user));
}

export const readUser: () => Partial<User> = () => {
  const user = localStorage.getItem('user');
  if (user) {
    return JSON.parse(user);
  }
  return {};
}

export const clearUser = () => {
  localStorage.removeItem('user');
}
