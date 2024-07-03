
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
    isAdmin?: number;
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
    sellCount: number;
    score?: number;
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
    paid: number;
  }

  interface Comment {
    id: number;
    content: string;
    productId: number;
    userId: number;
    createdAt: string;
    updatedAt: string;
    user?: User;
    product?: Product;
  }
  
  interface Rating {
    id: number;
    score: number;
    productId: number;
    userId: number;
    createdAt: string;
    updatedAt: string;
    user?: User;
    product?: Product;
  }
}
