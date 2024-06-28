
declare module 'app' {
  interface User {
    id: number;
    username: string;
    password: string;
    address: string;
    email: string;
    phone: string;
    gender: number;
    status: number;
    createdAt: string;
    updatedAt: string;
  }

  interface Category {
    id: number;
    name: string;
    code: string;
    description: string;
    parentId: number;
    createdAt: string;
    updatedAt: string;
  }

  interface Product {
    id: number;
    name: string;
    code: string;
    price: number;
    description: string;
    categoryId: number;
    createdAt: string;
    updatedAt: string;
    category?: Category;
  }

  interface Cart {
    id: number;
    productId: number;
    product?: Product;
    userId: number;
    user?: User;
    orderId: number;
    count: number;
    createdAt: string;
    updatedAt: string;
  }

  interface ShopOrder {
    id: number;
    carts?: Cart[];
    userId: number;
    user?: User;
    createdAt: string;
    updatedAt: string;
  }
}
