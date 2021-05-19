package com.lk.spring.transaction.propagation.service;

public interface TransactionPropagationService {
    void notransaction_exception_required_required();
    void notransaction_required_required_exception();
}
