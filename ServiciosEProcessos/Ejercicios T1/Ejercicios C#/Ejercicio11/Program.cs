using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ejercicio11
{
    class Program
    {
        static async Task Main(string[] args)
        {

            Paella p = new Paella();

            // Preparar caldo step 1.
            Cocinero cocinero = new Cocinero();
            var cocinarCaldoTask = cocinero.PrepararCaldo();

            // Calentar paella step 1-1
            await cocinero.CalentarPaella(p);

            // Sofreir ingiridientes 1-2
            await cocinero.SofreirIngredientes(p, new List<string>(){ "tomate", "pollo", "judías planas", "garrafó", "arroz"});
            
            // Espero que caldo esta hecho.
            Caldo c = await cocinarCaldoTask;

            // Echar el caldo
            cocinero.EcharCaldo(p, c);

            //Cocinar
            cocinero.Cocinar(p);

            Console.WriteLine("Paella lista: " + p);
            Console.ReadKey();

        }
    }
}
