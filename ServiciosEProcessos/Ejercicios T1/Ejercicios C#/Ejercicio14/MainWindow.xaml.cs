using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Ejercicio14
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        GestorFicheros gf = new GestorFicheros();

        public MainWindow()
        {
            InitializeComponent();
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {
            string projectDir;
            var directoryInfo = Directory.GetParent(Directory.GetCurrentDirectory())?.Parent;
            if (directoryInfo?.Parent != null)
            {
                projectDir = directoryInfo.FullName;
            }
            else
            {
                return;
            }

            const string filename = @"\EjerciciosSemana1.pdf"; // I was not downloading 4gb file to test if it works.
            var filePath = projectDir + filename;
            Console.WriteLine(filePath);
            label.Content = "Leyendo archivo";
            await gf.LeerArchivo(filePath);
            label.Content = "Archivo leído";
        }
    }
}
