import request from "@/utils/request";
import { Comment } from "app";

export const productComments = (productId: number) => {
  return request<Comment>({
    url: '/comment/productComments',
    method: 'get',
    params: {
      productId
    }
  })
}
