using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
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

namespace Ejercicio13
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {
            label.Content = "Descargando";

            await DownloadImageAsync();
            label.Content = "Archivo descargado";
        }

        // Well it isnt stuck... but asking to download 4gb file in a test app wasnt greatest idea.
        private async Task DownloadImageAsync()
        {
            using (WebClient client = new WebClient())
            {
                var url =
                    "https://fastly.picsum.photos/id/36/200/200.jpg?hmac=VnDu-KXiZmaBJk0XmixLx-JdUPLqVQtLdiqMXOn4LZc";
                var filename = "./picsum.jpg";
                await client.DownloadFileTaskAsync(
                    new Uri(url), filename);
            }
        }
    }
}