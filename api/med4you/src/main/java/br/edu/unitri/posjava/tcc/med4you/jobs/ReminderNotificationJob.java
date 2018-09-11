package br.edu.unitri.posjava.tcc.med4you.jobs;

import br.edu.unitri.posjava.tcc.med4you.model.Reminder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by pauloho on 10/09/18.
 */
public class ReminderNotificationJob implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        Reminder reminder = (Reminder) context.getMergedJobDataMap().get("REMINDER");
        //TROCAR AQUI PELA CHAMADA DO SERVICO REST QUE ENVIA AS NOTIFICAÇÕES
        System.out.println("SendNotification");

    }

}
