import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getProductList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/products/all`);
  }

  getProductById(pid:number): Observable<any> {
    return this.http.get(`${this.baseUrl}/products/${pid}`);
  } 

  getProductListByFilter(touchscreen: boolean, size: number, category: string, transportMonitor:boolean): Observable<any> {
    return this.http.get(`${this.baseUrl}/products?touchscreen=${touchscreen}&size=${size}&category=${category}&transportMonitor=${transportMonitor}`);
  } 

  addProduct(product: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/products/add`, product);
  }

  updateProduct(pid:number, product:Object): Observable<Object> {
    return this.http.put(`${this.baseUrl}/products/update/${pid}`, product);
  }

  deleteProduct(pid: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/products/delete/${pid}`, { responseType: 'text' });
  }
}