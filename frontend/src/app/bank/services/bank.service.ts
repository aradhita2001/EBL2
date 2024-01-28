
import { environment } from "src/environments/environment";
import { Transaction } from "../types/transaction";
import { Observable } from "rxjs";
import { Customer } from "../types/customer";
import { Account } from "../types/account";


export class BankService {
  
  

  addCustomer(customer: Customer): Observable<Customer> {
    
  }

  getCustomers(): Observable<Customer[]> {
    

  }

  addAccount(account: Account): Observable<Account> {
    
  }

  getAccounts(): Observable<Account[]> {
    

  }

  performTransaction(transaction: Transaction): Observable<Transaction> {
    
  }

  getOutstandingBalance(userId: string): Observable<number> {
    

  }

  getAllTranactions(): Observable<Transaction[]> {
   

  }
  /** get account by user */
  getAccountsByUser(userId:string|null): Observable<Account[]> {
    

  }

  getTransactionByUser(userId: string|null): Observable<Transaction[]> {
    

  }

  deleteCustomer(customerId: number): Observable<any> {
   
  }

  editCustomer(customer: Customer): Observable<Customer> {
    
  }

  deleteAccount(accountId: number): Observable<any> {
   
  }

  editAccount(account: Account): Observable<Account> {
    
  }


}
