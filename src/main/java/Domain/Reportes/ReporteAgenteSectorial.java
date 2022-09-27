package Domain.Reportes;

import Domain.Organizacion.AgenteSectorial;

public class ReporteAgenteSectorial extends Reportes{

    private AgenteSectorial agenteSectorial;

    public void reporteHC_SecTer(AgenteSectorial agente){
        
        Double cantidadHC = 0.0;

        for(int i = 2020; i < fechaActual; i++){
            for(int j = 1; j <= 12; j ++){
                cantidadHC = calculadorHC.calcularHC(agente, j, i);
            }
        }
        
    }
    
}
