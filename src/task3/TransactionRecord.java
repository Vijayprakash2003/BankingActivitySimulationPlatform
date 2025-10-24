package task3;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionRecord {
    private UUID txId;
    private String txType;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public TransactionRecord(String txType, String fromAccount, String toAccount, BigDecimal amount) {
        this.txId = UUID.randomUUID();
        this.txType = txType;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getTxId() { return txId; }
    public String getTxType() { return txType; }
    public String getFromAccount() { return fromAccount; }
    public String getToAccount() { return toAccount; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
