package br.edu.unitri.posjava.tcc.med4you.jobs;

import br.edu.unitri.posjava.tcc.med4you.model.Reminder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pauloho on 10/09/18.
 */
public class ReminderNotificationJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        Reminder reminder = (Reminder) context.getMergedJobDataMap().get("REMINDER");
        //TROCAR AQUI PELA CHAMADA DO SERVICO REST QUE ENVIA AS NOTIFICAÇÕES
        logger.info("Notificação enviada com sucesso!");

    }

}
