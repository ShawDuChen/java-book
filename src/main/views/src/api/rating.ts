import request from "@/utils/request";
import { Rating } from "app";

export const productRatings = (productId: number) => {
  return request<Rating>({
    url: '/rating/productRatings',
    method: 'get',
    params: {
      productId
    }
  })
}
