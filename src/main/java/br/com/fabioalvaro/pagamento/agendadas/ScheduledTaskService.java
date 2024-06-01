package br.com.fabioalvaro.pagamento.agendadas;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import br.com.fabioalvaro.pagamento.service.NotificationService;
import br.com.fabioalvaro.pagamento.service.PagamentoService;

@Service
public class ScheduledTaskService {
    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 10000) // 60000 milliseconds = 1 minute
    public void performTask() {
        System.out.println("Tarefa executada a cada 10 segundos: " + System.currentTimeMillis());
        // Adicione aqui a lógica que você deseja executar a cada 1 minuto
        List<Pagamento> pendingPayments = pagamentoService.getPendingPayments();
        for (Pagamento pagamento : pendingPayments) {
            System.out.println("Transacao ID: " + pagamento.getTransacaoId());
            // regra de valor maior que 200 - sempre negar
            if (pagamento.getAmount().compareTo(new BigDecimal("200")) > 0) {
                pagamento.setStatus("RECUSADO");
            } else {
                pagamento.setStatus("APROVADO");
            }

            pagamentoService.updatePagamento(pagamento);

            try {
                notificationService.sendNotification(pagamento.getCallback(), pagamento);
                System.out.println(">>> POST ENVIADO NO CALLBACK :: Transacao ID: " + pagamento.getTransacaoId());
            } catch (Exception e) {
                // Suprimir o erro, você pode adicionar um log aqui se desejar
                System.err.println("Erro ao enviar notificação: " + e.getMessage());
            }

        }

    }

    @Scheduled(cron = "0 * * * * ?") // Executa no início de cada minuto
    public void performTask2() {
        System.out.println("Tarefa executada no início de cada minuto: " + System.currentTimeMillis());
        // Adicione aqui a lógica que você deseja executar a cada 1 minuto
    }
}